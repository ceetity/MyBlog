<template>
  <div class="page-container">
    <div class="login-container" v-if="!isLoggedIn">
      <el-button type="primary" @click="goToLogin">登录</el-button>
    </div>
    <div class="login-container" v-else>
      <el-button type="danger" @click="handleLogout">退出</el-button>
    </div>

    <div class="home" v-if="isLoggedIn">
      <div class="welcome-banner">
        <el-image class="banner-image" src="/images/yunyun.png" fit="cover" />
        <div class="banner-content">
          <h1>欢迎访问我的网站</h1>
          <p>分享诗歌，畅谈人生</p>
        </div>
      </div>

      <div class="latest-articles">
        <h2>最新文章</h2>
        <div class="article-cards">
          <el-card
            v-for="article in latestArticles"
            :key="article.id"
            class="article-item"
            @click="viewArticle(article.id)"
          >
            <h3>{{ article.title }}</h3>
            <p class="description">{{ article.description }}</p>
            <div class="meta">
              <span>
                <el-icon><Calendar /></el-icon>
                {{ formatDate(article.createTime) }}
              </span>
              <span>
                <el-icon><View /></el-icon>
                {{ article.views }}
              </span>
            </div>
          </el-card>
        </div>
      </div>

      <div class="categories" v-if="categories.length">
        <h2>文章分类</h2>
        <div class="category-list">
          <el-tag
            v-for="category in categories"
            :key="category.id"
            @click="filterByCategory(category.id)"
            class="category-tag"
          >
            {{ category.name }}
          </el-tag>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import { Calendar, View } from "@element-plus/icons-vue";
import request from "../utils/request";
import { ElMessage } from "element-plus";

const router = useRouter();
const latestArticles = ref([]);
const categories = ref([]);

const isLoggedIn = computed(() => {
  return !!localStorage.getItem("token");
});

const formatDate = (dateStr) => {
  if (!dateStr) return "";
  const date = new Date(dateStr);
  return date.toLocaleDateString();
};

const fetchLatestArticles = async () => {
  try {
    const response = await request.get("/api/articles/latest");
    latestArticles.value = response.data.data || [];
  } catch (error) {
    console.error("获取最新文章失败:", error);
    ElMessage.error("获取最新文章失败");
  }
};

const fetchCategories = async () => {
  try {
    const response = await request.get("/api/categories");
    categories.value = response.data.data || [];
  } catch (error) {
    console.error("获取文章分类失败:", error);
    ElMessage.error("获取文章分类失败");
  }
};

const viewArticle = (id) => {
  router.push(`/article/${id}`);
};

const filterByCategory = (categoryId) => {
  router.push({
    path: "/articles",
    query: { category: categoryId }
  });
};

const goToLogin = () => {
  router.push("/login");
};

const handleLogout = () => {
  localStorage.removeItem("token");
  ElMessage.success("退出登录成功");
  router.push("/login");
};

onMounted(() => {
  if (isLoggedIn.value) {
    fetchLatestArticles();
    fetchCategories();
  }
  // 禁用右键菜单
  document.addEventListener("contextmenu", (event) => {
    event.preventDefault();
  });
});
</script>

<style scoped>
.page-container {
  position: relative;
  min-height: 100vh;
  width: 100%;
}

.login-container {
  position: fixed;
  top: 20px;
  right: 40px;
  z-index: 1000;
}

.home {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.welcome-banner {
  position: relative;
  text-align: center;
  padding: 40px 20px;
  border-radius: 8px;
  margin-bottom: 40px;
  overflow: hidden;
  height: 300px;
}

.banner-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 0;
  object-fit: cover;  /* 添加这个属性 */
}

.banner-content {
  position: relative;
  z-index: 1;
  color: white;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);  /* 添加文字阴影 */
}

.banner-content h1 {
  margin: 0;
  font-size: 2.5em;
}

.banner-content p {
  margin: 10px 0 0;
  font-size: 1.2em;
  opacity: 0.9;
}

.article-cards {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  margin-top: 20px;
}

.article-item {
  cursor: pointer;
  transition: all 0.3s ease;
  background-color: white;  /* 添加背景色 */
}

.article-item:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.article-item h3 {
  margin: 0 0 10px 0;
  color: #303133;
}

.description {
  color: #606266;
  margin: 10px 0;
  line-height: 1.5;  /* 添加行高 */
}

.meta {
  display: flex;
  gap: 20px;
  color: #909399;
  font-size: 14px;
  align-items: center;
}

.meta span {
  display: flex;
  align-items: center;
  gap: 4px;
}

.categories {
  margin-top: 40px;
}

.category-list {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 20px;
}

.category-tag {
  cursor: pointer;
  transition: all 0.3s ease;
  padding: 8px 16px;  /* 添加内边距 */
}

.category-tag:hover {
  transform: translateY(-2px);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);  /* 添加阴影效果 */
}

h2 {
  color: #303133;
  margin-bottom: 20px;
  font-size: 1.8em;  /* 调整标题大小 */
}
</style>