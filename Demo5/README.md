两个按钮，一个拍照存默认相册并显示，一个拍照存自定义文件并显示。build.gradle中targetSdkVersion 22，否则targetSdkVersion 23时文件IO在6.0上有权限问题。

用到的：
View.OnClickListener,
MediaStore.ACTION_IMAGE_CAPTURE,
ImageView,
Bitmap,
File