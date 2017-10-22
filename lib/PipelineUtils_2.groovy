package lib

class PipelineUtils2 implements Serializable{
	def name
	
	String calculateName() {
		return "Hello: ${name}"
	}
}

def gogo(abc) {
	println 'ho abc:'+abc
}


// this method just to have nice access to create class by name
Object getProperty(String name){
	return this.getClass().getClassLoader().loadClass(name).newInstance();
}

def instance() {
	println 'new instance created'
	return new PipelineUtils2()
}

return this;