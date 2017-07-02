package cn.com.bigFs.domain;

/**
 * http璇锋眰杩斿洖鐨勬渶澶栧眰瀵硅薄 Created by zjk
 */
public class Result<T> {

	/** 閿欒鐮�. */
	private Integer code;
	
	/** 閿欒鐮�. */
	private Boolean result;

	/** 鎻愮ず淇℃伅. */
	private String desc;

	/** 鍏蜂綋鐨勫唴瀹�. */
	private T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Result<T> success(T object) {
		this.setCode(0);
		this.setDesc("成功");
		this.setData(object);
		this.setResult(true);
		return this;
	}

	public Result<T> success() {
		return success(null);
	}

	public Result<T> error(Integer code, String msg) {
		this.setCode(code);
		this.setDesc(msg);
		this.setResult(false);
		return this;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

}
