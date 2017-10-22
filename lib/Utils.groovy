package lib

def setName(n) {
	name=n
}
def showName() { 
	println 'showName:'+name+' '+'lastname'
}


def initialize(n,lastname) {
	name=n
	lastname=lastname
}

return this;