<template>
  <el-container class="layout-container">
    <el-header class="fixed-header">
      <el-menu mode="horizontal" router>
        <el-menu-item index="/">首页</el-menu-item>
        <el-menu-item index="/articles">文章</el-menu-item>
        <el-menu-item index="/about">关于</el-menu-item>
      </el-menu>
    </el-header>

    <el-main>
      <router-view></router-view>
    </el-main>

    <el-footer>
      <p>&copy; 2024 我的博客 | All Rights Reserved</p>
    </el-footer>
  </el-container>
</template>

<script setup>
// 不需要单独引入组件，因为我们在 main.js 中全局引入了 Element Plus
</script>

<style>
.layout-container {
  min-height: 100vh;
  padding-top: 64px; /* Adjust this value based on the header height */
}

.fixed-header {
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 1000;
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.el-footer {
  text-align: center;
  background-color: #f5f5f5;
  padding: 20px;
}

.el-main {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}
</style>