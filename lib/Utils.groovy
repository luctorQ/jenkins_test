package lib

def setName(n) {
	name=n
}
def showName() { 
	println 'showName:'+name+' '+lastname
}


def initialize(n,l) {
	name=n
	lastname=l
}

return this;