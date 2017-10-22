package lib

def setName(n) {
	name=n
}
def showName() { 
	println 'showName:'+name
}


def initialize(name,lastname) {
	this.name=name
	this.lastname=lastname
}

return this;