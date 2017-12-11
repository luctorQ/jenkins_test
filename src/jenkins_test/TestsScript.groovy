LATEST_SVN_REVISIONS=[ab:26399, bc:26662, pc:26659, cc:26659]
def predicted_revisions=[ab:26399, bc:26615, cc:26632, pc:26611, ec:25718]

def matchLatestRevisions=LATEST_SVN_REVISIONS.inject(true,{result,entry->
    entry.value==predicted_revisions[entry.key] && result
})
println 'matchLatestRevisions:'+matchLatestRevisions

ABC=[]
if(ABC.size()>0 || !matchLatestRevisions) {
	println 'go'
}