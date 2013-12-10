package shuimin.atws.core.kernel.resource;

import java.io.File;

import com.shuimin.base.A;

public class FileResource extends RegularResource
{
	private File file ;
	protected FileResource(File f){
		file = f;
	}
	
	@Override
	public File read(Object r) throws Exception
	{
		A._assert(file != null && file.canRead(), "file not access");
		return file;
	}
}
