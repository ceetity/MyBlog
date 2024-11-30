<template>
  <div class="article-detail">
    <el-card v-loading="loading">
      <template v-if="article">
        <h1>{{ article.title }}</h1>
        <div class="meta">
          <span>浏览量: {{ article.views }}</span>
          <span>发布时间: {{ formatDate(article.createTime) }}</span>
        </div>
        <div class="content">
          {{ article.content }}
        </div>
      </template>
      <el-empty v-else description="文章不存在"></el-empty>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'
import { ElMessage } from 'element-plus'

const route = useRoute()
const article = ref(null)
const loading = ref(false)

const formatDate = (dateStr) => {
  if (!dateStr) return ''
  const date = new Date(dateStr)
  return date.toLocaleDateString()
}

const fetchArticle = async () => {
  loading.value = true
  try {
    const response = await axios.get(`http://localhost:8083/api/articles/${route.params.id}`)
    article.value = response.data.data
  } catch (error) {
    console.error('获取文章详情失败:', error)
    ElMessage.error('获取文章详情失败')
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchArticle()
})
</script>

<style scoped>
.article-detail {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.meta {
  display: flex;
  gap: 20px;
  color: #909399;
  font-size: 14px;
  margin: 20px 0;
}

.content {
  line-height: 1.8;
  color: #303133;
  margin-top: 30px;
  white-space: pre-wrap;
}

h1 {
  margin: 0;
  color: #303133;
}
</style>