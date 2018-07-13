enablePlugins(Fs2Grpc)

libraryDependencies += "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf"

libraryDependencies ++= Seq(
    "com.google.guava" % "guava" % "19.0",
    "com.google.guava" % "guava" % "19.0",
    "com.google.auth" % "google-auth-library-oauth2-http" % "0.10.0",
    "io.grpc" % "grpc-netty" % "1.12.0",
    //"io.netty" % "netty-all" % "4.1.27.Final",
     "io.grpc" % "grpc-auth" % "1.12.0",
     "io.netty" % "netty-tcnative-boringssl-static" % "2.0.7.Final",
)
