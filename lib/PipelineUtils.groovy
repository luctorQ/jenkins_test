package lib

class PipelineUtils implements Serializable{
	def name
	
	String calculateName() {
		return "Hello: ${name}"
	}
}
