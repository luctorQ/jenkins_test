package lib

class PipelineUtils implements Serializable{
	def name
	
	String calculateName() {
		return "Hello: ${name}"
	}
}



// this method just to have nice access to create class by name
Object getProperty(String name){
	return this.getClass().getClassLoader().loadClass(name).newInstance();
}

return this;