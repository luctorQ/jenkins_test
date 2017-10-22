package lib

import javax.swing.GraphicsWrapper

import groovy.transform.Field

APPS_DICT=[PC:'pc',BC:'bc',CC:'cc',AB:'ab'] 


class Context implements Serializable{
	def List<Revision> requestedRevisions=[]

	class Revision {
		String appname
		Integer revision
		
		Revision(String appname, Ingeter revision) {
			this.appname=appname
			this.revision=revision
		}
	}
		
}


enum GW_APPS{
	PC("pc"),BC("bc"),CC("cc"),CM("cm")
	
	private final String name
	
	GW_APPS(String name) {
		this.name=name
	}
	
	String toString() {
		return this.name
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
	
	println APPS_DICT.ab
	
}


return this;