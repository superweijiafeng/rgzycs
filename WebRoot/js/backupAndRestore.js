function warnFile(){
	return window.confirm("警告：这将恢复网站数据，同名文件将被覆盖");
}

function warnDatabase(){
	return window.confirm("警告：这将清空数据库，并恢复成文件中的数据内容");
}

function warnDatabaseStructure(){
	return window.confirm("警告：这将清空并重建数据库，数据将丢失，请确认已先备份数据库");
}