<template>
  <div>
    <!-- 文件上传 -->
    <form
      class="upload"
      action="http://uxnbis.natappfree.cc/authentication/upLoad"
      method="post"
      enctype="multipart/form-data"
    >
      <input type="file" name="file" />
      <input type="submit" value="上传文件" class="upload-file" />
    </form>
    <!-- 文件下载 -->
    <form
      class="download-container"
      action="http://uxnbis.natappfree.cc/authentication/xiazai"
      method="post"
      enctype="multipart/form-data"
    >
      <div class="container">
        <span class="course-name">
          课程名称
          <input type="text" name="courseName" class="courseName" />
        </span>

        <span>
          版本
          <input type="text" name="version" class="version" />
        </span>
      </div>

      <div class="download">
        <input type="submit" value="下载文件" class="download-file" />
      </div>
    </form>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.upload {
  text-align: center;
  border: 1px solid #ccc;
  width: 600px;
  margin: 30px auto;
  height: 100px;
  line-height: 100px;
  border-radius: 10px;
}

.upload-file {
  width: 100px;
  height: 25px;
  background: #2795e9;
  color: white;
}

.download-container {
  margin-top: 50px;
  border: 1px solid #ccc;
  width: 600px;
  border-radius: 10px;
  margin: 60px auto;
  height: 200px;
  text-align: center;
}

.container {
  margin: 0 auto;
  margin-top: 60px;
  width: 500px;
  height: 50px;
  line-height: 50px;
}

.container span.course-name {
  margin-right: 100px;
}

.container span .version {
  border: 1px solid #ccc;
  height: 20px;
  padding: 3px;
  width: 50px;
  text-align: center;
  outline: none;
}

.courseName {
  width: 100px;
  border: 1px solid #ccc;
  height: 20px;
  padding: 3px;
  text-align: center;
  outline: none;
}

.download {
  margin-top: 20px;
  margin: 20px auto;
}

.download-file {
  width: 100px;
  height: 25px;
  background: #2795e9;
  color: white;
}
</style>
