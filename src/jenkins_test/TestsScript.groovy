package jenkins_test

def test=[aa:'okok']

println test?test:'nothing'

def method(first,opt=null) {
	
	println ('first:'+first+' opt:'+opt)
}


method(12)