ContentProvider读取通讯录联系人，显示为列表。

用到的：
ContextCompat.checkSelfPermission,
Manifest.permission.READ_CONTACTS,
PackageManager.PERMISSION_GRANTED,
ActivityCompat.requestPermissions,
onRequestPermissionsResult,
ArrayAdapter,
notifyDataSetChanged,
getContentResolver,
ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
ContactsContract.CommonDataKinds.Phone.NUMBER,
Cursor

