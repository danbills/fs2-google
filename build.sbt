import sbtassembly.MergeStrategy
enablePlugins(Fs2Grpc)

libraryDependencies += "com.thesamet.scalapb" %% "scalapb-runtime" % "0.8.0-SNAPSHOT" % "protobuf"

libraryDependencies ++= Seq(
    "com.google.guava" % "guava" % "19.0",
    "com.google.guava" % "guava" % "19.0",
    "com.google.auth" % "google-auth-library-oauth2-http" % "0.10.0",
    "io.grpc" % "grpc-netty" % "1.12.0",
    //"io.netty" % "netty-all" % "4.1.27.Final",
     "io.grpc" % "grpc-auth" % "1.12.0",
     "io.netty" % "netty-tcnative-boringssl-static" % "2.0.7.Final",
)

val defaultMergeStrategy: String => MergeStrategy = {
  case x if Assembly.isConfigFile(x) =>
    MergeStrategy.concat
  case PathList(ps @ _*) if Assembly.isReadme(ps.last) || Assembly.isLicenseFile(ps.last) =>
    MergeStrategy.rename
  case PathList("META-INF", xs @ _*) =>
    (xs map {_.toLowerCase}) match {
      case ("manifest.mf" :: Nil) | ("index.list" :: Nil) | ("dependencies" :: Nil) =>
	MergeStrategy.discard
      case ps @ (x :: xs) if ps.last.endsWith(".sf") || ps.last.endsWith(".dsa") =>
	MergeStrategy.discard
      case "plexus" :: xs =>
	MergeStrategy.discard
      case "services" :: xs =>
	MergeStrategy.filterDistinctLines
      case ("spring.schemas" :: Nil) | ("spring.handlers" :: Nil) =>
	MergeStrategy.filterDistinctLines
      case _ => MergeStrategy.first
    }
  case _ => MergeStrategy.first
}

assemblyMergeStrategy in assembly := defaultMergeStrategy

PB.protocVersion := "-v360"

