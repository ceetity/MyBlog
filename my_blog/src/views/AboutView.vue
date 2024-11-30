<template>
  <div class="about">
    <!-- 使用 el-card 组件创建一个卡片,并添加加载状态 -->
    <el-card class="about-card" v-loading="loading">
      <h1>关于我</h1>
      <el-divider></el-divider>
      
      <!-- 个人信息部分,只在 profile 数据存在时显示 -->
      <div class="profile" v-if="profile">
        <!-- 显示头像,使用 el-avatar 组件,设置大小为 100 -->
        <el-avatar 
          :size="100" 
          :src="avatarUrl"   
          class="avatar">
        </el-avatar>
        <!-- 显示姓名 -->
        <h2>{{ profile.name }}</h2>
        <!-- 显示职位 -->
        <p class="title">{{ profile.title }}</p>
      </div>
      
      <!-- 个人简介部分,只在 profile 数据存在时显示 -->
      <div class="description" v-if="profile">
        <!-- 这里可以显示个人简介,目前被注释掉了 -->
        <p>{{ profile.description }}</p>
      </div>

      <!-- 技能标签部分,只在 profile.skills 数组存在且不为空时显示 -->
      <div class="skills" v-if="profile?.skills?.length || []">
        <h3>技能专长</h3>
        <div class="skill-tags">
          <!-- 使用 v-for 指令遍历 profile.skills 数组,为每个技能创建一个 el-tag 标签 -->
          <el-tag 
          v-for="skill in (profile?.skills || [])" 
            :key="skill"
            class="skill-tag"
          >
            {{ skill }}
          </el-tag>
        </div>
      </div>

      <!-- 联系方式部分,只在 profile 数据存在时显示 -->
      <div class="contact" v-if="profile">
        <h3>联系方式</h3>
        <el-row>
          <el-col :span="12">
            <!-- 显示邮箱,使用 el-icon 组件显示图标 -->
            <p><el-icon><Message /></el-icon> {{ profile.email }}</p>
          </el-col>
          <el-col :span="12">
            <!-- 显示 GitHub 链接,使用 el-icon 组件显示图标 -->
            <p><el-icon><Link /></el-icon> {{ profile.github }}</p>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { Message, Link } from '@element-plus/icons-vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import avatarUrl from '@/assets/Kagura.png'

const profile = ref(null)
const loading = ref(false)

const fetchProfile = async () => {
  loading.value = true
  try {
    // 直接使用后端服务器的完整 URL
    const response = await axios.get('http://localhost:8083/api/about')
    profile.value = response.data.data
  } catch (error) {
    console.error('获取个人信息失败:', error)
    ElMessage.error('获取个人信息失败了')
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchProfile()
})
</script>

<style scoped>
/* 这里是组件的样式,使用 scoped 属性确保样式只在当前组件生效 */
.about {
  max-width: 800px;
  margin: 20px auto;
  padding: 0 20px;
}

.about-card {
  margin-bottom: 20px;
}

.profile {
  text-align: center;
  margin: 30px 0;
}

.profile h2 {
  margin: 15px 0 5px 0;
  color: #303133;
}

.profile .title {
  color: #909399;
  margin: 0;
}

.description {
  margin: 30px 0;
  line-height: 1.8;
  color: #606266;
}

.skills {
  margin: 30px 0;
}

.skill-tags {
  margin-top: 15px;
}

.skill-tag {
  margin: 0 10px 10px 0;
}

.contact {
  margin-top: 30px;
}

.contact p {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #606266;
}

.el-icon {
  color: #409EFF;
}

h3 {
  color: #303133;
  margin-bottom: 15px;
}
</style>