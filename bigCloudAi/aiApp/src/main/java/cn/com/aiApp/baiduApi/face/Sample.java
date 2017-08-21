package cn.com.aiApp.baiduApi.face;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

public class Sample {
	//设置APPID/AK/SK
    public static final String APP_ID = "9955600";
    public static final String API_KEY = "Gf5I1OI5YkAT81lYEms23fEG";
    public static final String SECRET_KEY = "IRXX3TVSTik9HgHISUyWk7G27BSRjUFc";

    public static void main(String[] args) throws Exception {

        // 初始化一个FaceClient
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用API
        String image = "E:\\git\\bigCloudAI\\bigCloudAi\\bigCloudAi\\bigCloudAi\\aiApp\\src\\lib\\9.jpg";
        
        HashMap<String, String> options = new HashMap<String, String>();
        
        options.put("face_fields", "age,beauty,expression");
        options.put("max_face_num", "100");
       // options.put("face_fields", "expression");

        // 参数为本地图片路径
        JSONObject response = client.detect(image, options);
        System.out.println(response.toString());

        // 参数为本地图片文件二进制数组
     /*   byte[] file = readImageFile(imagePath);
        JSONObject response = client.detect(file, options);
        System.out.println(response.toString());
        */
        /*JSONObject res = client.detect(image, new HashMap<String, String>());
        
        System.out.println(res.toString(2));*/
    }
    
}
