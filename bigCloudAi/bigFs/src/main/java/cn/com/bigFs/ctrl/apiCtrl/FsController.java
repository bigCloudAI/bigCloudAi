package cn.com.bigFs.ctrl.apiCtrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.com.bigFs.domain.Result;
import cn.com.bigFs.repo.FsNodeRepo;
import cn.com.bigFs.service.FsService;

@RestController
@RequestMapping("/fs")
public class FsController {
	
	/*@RequestMapping(value = "joinChain/{ip}",method = RequestMethod.GET)
	public Result<String> joinChain(@PathVariable String ip) {*/
	
	@Autowired
	FsNodeRepo fsNodeRepo;
	
	@Autowired
	FsService fsService;
	
	@RequestMapping(value = "/ls/{where}",method = RequestMethod.GET)
	public Result<Object> ls(@PathVariable String where) throws Exception {
		return new Result<Object>().success(fsService.findFsNodeChildNode(where));
	}
	
	@RequestMapping(value = "/mkdir/{where}/{dirName}",method = RequestMethod.GET)
	public Result<String> mkdir(@PathVariable String where,@PathVariable String dirName) throws Exception {
		return new Result<String>().success(fsService.fsNodeMkdir(where,dirName));
	}
	
	@RequestMapping(value = "/put/{where}/{fileName}",method = RequestMethod.POST)
	public Result<String> put(@PathVariable String where,@PathVariable String fileName, MultipartFile partFile) throws Exception {
		return new Result<String>().success(fsService.fsNodePut(where, fileName, partFile));
	} 
	
	
}
