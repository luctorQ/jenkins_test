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
	u2=load 'lib/Utils2.groovy'
	this.name=n
	this.lastname=l
}

def runU2() {
	u2.name='hello'
	u2.showName()
}


return this;