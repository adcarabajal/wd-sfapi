package sfapi.core
{
	public class AppTreeNode
	{
		public function AppTreeNode(setchild:Object = null, setindex:int = 0, setisRaw:Boolean = false)
		{
			child = setchild;
			index = setindex;
			isRaw = setisRaw;
		}
		public var child:Object;
		public var index:int; 
		public var isRaw:Boolean;
	}
}