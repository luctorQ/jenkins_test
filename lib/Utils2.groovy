package lib

import groovy.transform.Field

@Field
def name
@Field
def lastname

def setName(name) {
	this.name=name
}

def showName() { 
	println 'showName:'+this.name+' '+this.lastname
}


def initialize(n,l) {
	
	this.name=n
	this.lastname=l
}

return this;