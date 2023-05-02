//Refer to video on youtube : https://youtu.be/zW8oXDkUxCM or https://raw.githubusercontent.com/VaibhavMojidra/Kotlin---Demo-Delegation/master/reference_video/Delegation_In_Kotlin.mp4

interface Downloader{
	fun download()
}

interface Uploader{
	fun upload()
}


class VideoDownloader(val filename:String):Downloader{
	override fun download(){
		println("Downloaded video $filename")
	}
}

class VideoUploader(val filename:String):Uploader{
	override fun upload(){
		println("Uploaded video $filename")
	}
}

class ImageDownloader(val filename:String):Downloader{
	override fun download(){
		println("Downloaded image $filename")
	}
}

class ImageUploader(val filename:String):Uploader{
	override fun upload(){
		println("Uploaded image $filename")
	}
}

//class FileHandler(var downloader:Downloader,var uploader:Uploader):Downloader,Uploader{
//	override fun download(){
//		downloader.download()
//	}
//	override fun upload(){
//		uploader.upload()
//	}
//	
//}


//Same Above code can be return using native delegation which kotlin provides, bascially we don't want to implement simple calling like above
class FileHandler(var downloader:Downloader,var uploader:Uploader):Downloader by downloader,Uploader by uploader

fun main(args: Array<String>) {
	
	val videoDownloadFile=VideoDownloader("Video2.mp4")
	val videoUploadFile=VideoUploader("Video1.mp4")
	
	val imageDownloadFile=ImageDownloader("Sample2.png")
	val imageUploadFile=ImageUploader("Sample1.png")
	
	
	val fileHandler=FileHandler(videoDownloadFile,videoUploadFile)
	
	fileHandler.upload()
	fileHandler.download()
	
	fileHandler.uploader=imageUploadFile
	fileHandler.downloader=imageDownloadFile
	
	fileHandler.upload()
	fileHandler.download()
	
}