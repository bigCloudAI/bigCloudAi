package cn.com.bigFs.test.repo.ctrl.apiCtrl;

import java.io.File;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class FsControllerTest {
	@Test
	public void testUpload() throws Exception {
		String url = "http://127.0.0.1:6066/fs/put/>/zjk.keystore";
		String filePath = "G:\\zjk.keystore";

		RestTemplate rest = new RestTemplate();
		FileSystemResource resource = new FileSystemResource(new File(filePath));
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("partFile", resource);
		String string = rest.postForObject(url, param, String.class);
		System.out.println(string);
	}
}
