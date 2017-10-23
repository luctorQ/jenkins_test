package lib

import groovy.transform.Field
import lib.Utils2

APPS_DICT=[PC:'pc',BC:'bc',CC:'cc',AB:'ab'] 

class Revision implements Serializable{
	String appname
	Integer revision
	
	Revision(String appname, Integer revision) {
		this.appname=appname
		this.revision=revision
	}
}

class BuildContext implements Serializable{	
	List<Revision> requestedRevisions=[]
	
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
	String toString(){
		return "revbc:${this.revbc},revpc:${this.revpc}" 
	}
}


def aaa=[revbc:56,revpc:88]


Context c=aaa as Context


println 'map as context:'+c.toString()




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