package manytag.business.base;

public interface ICommonProcess {
	// action方法前置处理
	public void doActionBefor() throws Exception;

	// action方法后置处理
	public void doActionAfter() throws Exception;
}