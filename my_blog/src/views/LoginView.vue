<template>
    <div class="login-container">
      <div class="login-box">
        <h2>登录</h2>
        <el-form :model="loginForm" :rules="rules" ref="loginFormRef">
          <el-form-item prop="email">
            <el-input
              v-model="loginForm.email"
              placeholder="请输入邮箱"
              :prefix-icon="Message"
            />
          </el-form-item>
          
          <el-form-item prop="code" class="verification-code">
            <el-input
              v-model="loginForm.code"
              placeholder="请输入验证码"
              :prefix-icon="Lock"
            >
              <template #append>
                <el-button 
                  type="primary" 
                  @click="sendCode" 
                  :disabled="isCodeSending"
                >
                  {{ codeButtonText }}
                </el-button>
              </template>
            </el-input>
          </el-form-item>
  
          <el-form-item>
            <el-button type="primary" @click="handleLogin" class="login-button">
              登录
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, computed } from "vue";
  import { useRouter, useRoute } from "vue-router";
  import { Message, Lock } from "@element-plus/icons-vue";
  import { ElMessage } from "element-plus";
  import request from "../utils/request";
  
  const router = useRouter();
  const route = useRoute();
  const loginFormRef = ref(null);
  const countdown = ref(0);
  const isCodeSending = ref(false);
  
  const loginForm = ref({
    email: "",
    code: "",
  });
  
  const rules = {
    email: [
      { required: true, message: "请输入邮箱", trigger: "blur" },
      { type: "email", message: "请输入正确的邮箱格式", trigger: "blur" },
    ],
    code: [
      { required: true, message: "请输入验证码", trigger: "blur" },
      { len: 6, message: "验证码长度应为6位", trigger: "blur" },
    ],
  };
  
  const codeButtonText = computed(() => {
    if (countdown.value > 0) {
      return `${countdown.value}秒后重试`;
    }
    return "发送验证码";
  });
  
  const startCountdown = () => {
    countdown.value = 60;
    isCodeSending.value = true;
    const timer = setInterval(() => {
      countdown.value--;
      if (countdown.value <= 0) {
        clearInterval(timer);
        isCodeSending.value = false;
      }
    }, 1000);
  };
  
  const sendCode = async () => {
    try {
      await loginFormRef.value.validateField("email");
      const response = await request.post(
        `/api/user/email/code?email=${loginForm.value.email}`
      );
      if (response.data.code === 200) {
        ElMessage.success("验证码已发送到邮箱");
        startCountdown();
      } else {
        ElMessage.error(response.data.message || "发送验证码失败");
      }
    } catch (error) {
      console.error("发送验证码失败:", error);
      ElMessage.error("发送验证码失败");
    }
  };
  
  const handleLogin = async () => {
    if (!loginFormRef.value) return;
    
    try {
      await loginFormRef.value.validate();
      const response = await request.post("/api/user/login", {
        email: loginForm.value.email,
        code: loginForm.value.code,
      });
      
      if (response.data.code === 200) {
        ElMessage.success("登录成功");
        localStorage.setItem("token", response.data.data);
        
        const redirect = route.query.redirect || "/";
        router.push(redirect);
      } else {
        ElMessage.error(response.data.message || "登录失败");
      }
    } catch (error) {
      console.error("登录失败:", error);
      ElMessage.error("登录失败");
    }
  };
  </script>
  
  <style scoped>
  .login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-color: #f5f7fa;
  }
  
  .login-box {
    width: 400px;
    padding: 40px;
    background: white;
    border-radius: 8px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }
  
  .login-box h2 {
    text-align: center;
    margin-bottom: 30px;
    color: #303133;
  }
  
  .verification-code {
    margin-bottom: 20px;
  }
  
  .login-button {
    width: 100%;
  }
  
  :deep(.el-input-group__append) {
    padding: 0;
  }
  
  :deep(.el-input-group__append button) {
    border: none;
    margin: 0;
    height: 100%;
  }
  </style>