import axios from "axios";
import router from "../router";
import { ElMessage } from "element-plus";

const request = axios.create({
  baseURL: "http://localhost:8083",
  timeout: 5000,
  withCredentials: true  // 改为 true
});

request.interceptors.request.use(
  config => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.authorization = token;  // 使用小写的 authorization
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

request.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    if (error.response) {
      switch (error.response.status) {
        case 401:
          localStorage.removeItem("token");
          router.push({
            path: "/login",
            query: { redirect: router.currentRoute.value.fullPath }
          });
          ElMessage.error("请先登录");
          break;
        case 403:
          ElMessage.error("没有权限访问");
          break;
        default:
          ElMessage.error(error.response.data.message || "请求失败");
      }
    } else if (error.code === "ERR_NETWORK") {
      ElMessage.error("网络连接失败，请检查后端服务是否启动");
    } else {
      ElMessage.error("请求失败，请稍后重试");
    }
    return Promise.reject(error);
  }
);

export default request;