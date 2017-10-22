package lib

import javax.swing.GraphicsWrapper

import groovy.transform.Field

enum GW_APPS{
	PC,BC,CC,CM
	
	GW_APPS(int value) {
		this.value = value
	}
	private final int value
	
	int getValue() {
		value
	}
}

class BuildInfo implements Serializable{
	Integer svnrevision
	Integer jobid
	
	BuildInfo(svnrevision,jobid) {
		this.svnrevision=svnrevision
		this.jobid=jobid
	}
	
	
}

class Context implements Serializable {
	
	Integer revpc
	Integer revbc
	
	def comare() {
		return revpc<revbc
	}
}


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
	u2.setName('hello')
	u2.showName()
	Context ctx=new Context();
	ctx.revbc=443
	
	String pc='PC'
	
	def enumed=pc as GW_APPS
	println 'enumed:'+enumed
	
	
}


return this;