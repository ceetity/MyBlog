<template>
  <div class="articles">
    <h1>文章列表</h1>
    <div v-if="articles.length > 0" class="articles-list">
      <el-card v-for="article in articles" 
              :key="article.id" 
              class="articles-item"
              @click="goToArticle(article.id)">
        <h2>{{ article.title }}</h2>
        <p class="description">{{ article.description }}</p>
        <div class="meta">
          <span>浏览量: {{ article.views }}</span>
          <span>发布时间: {{ formatDate(article.createTime) }}</span>
        </div>
      </el-card>
    </div>
    <el-empty v-else description="暂无文章"></el-empty>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'

const router = useRouter()
const route = useRoute()
const articles = ref([])

const formatDate = (dateStr) => {
  if (!dateStr) return ''
  const date = new Date(dateStr)
  return date.toLocaleDateString()
}

const goToArticle = (id) => {
  router.push(`/article/${id}`)
}

const fetchArticles = async (categoryId) => {
  try {
    let response;
    if (categoryId) {
      response = await axios.get(`http://localhost:8083/api/articles/category/${categoryId}`)
    } else {
      response = await axios.get('http://localhost:8083/api/articles')
    }
    articles.value = response.data.data || []
  } catch (error) {
    console.error('获取文章列表失败:', error)
    ElMessage.error('获取文章列表失败')
  }
}

watch(() => route.query.category, (newCategoryId) => {
  fetchArticles(newCategoryId)
})

onMounted(() => {
  fetchArticles(route.query.category)
})
</script>

<style scoped>
.articles {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.articles-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.articles-item {
  margin-bottom: 20px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.articles-item:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.articles-item h2 {
  margin: 0 0 10px 0;
  color: #303133;
}

.description {
  color: #606266;
  margin: 10px 0;
}

.meta {
  display: flex;
  gap: 20px;
  color: #909399;
  font-size: 14px;
}
</style>