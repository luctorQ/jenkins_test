package jenkins_test

def test=[aa:'okok']

println test?test:'nothing'

def method(first,opt='UNDEFINED') {
	println ('first:'+first+' opt:'+(opt?:'abc'))
}


method(12,null)