import ammonite.ops._
import $ivy.`com.google.guava:guava:19.0`
interp.load.cp(pwd/'target/"scala-2.12"/"fs2-google-assembly-0.1.0-SNAPSHOT.jar")
//import $ivy.`io.grpc:grpc-auth:1.13.0`
import $ivy.`com.google.auth:google-auth-library-oauth2-http:0.6.0`
import $ivy.`io.grpc:grpc-netty:1.12.0`
import $ivy.`io.grpc:grpc-stub:1.12.0`
import $ivy.`io.grpc:grpc-protobuf:1.12.0`
import $ivy.`io.grpc:grpc-auth:1.12.0`

@

import collection.JavaConverters._
import com.google.auth.oauth2.{GoogleCredentials, UserCredentials}

val OAUTH2_SCOPES = List("https://www.googleapis.com/auth/cloud-platform").asJava

import cats.effect.IO
import com.google.datastore.v1.datastore.RunQueryRequest
import com.google.datastore.v1.datastore.RunQueryRequest.QueryType.{Query => DQuery}
import com.google.datastore.v1.query._
import com.google.datastore.v1.query.PropertyOrder.Direction
import fs2._
import io.grpc._
import org.lyranthe.fs2_grpc.java_runtime.implicits._
import scala.concurrent.ExecutionContext.Implicits.global

def channel(host: String, port: Int): ManagedChannel = {
  val creds = GoogleCredentials.getApplicationDefault().createScoped(OAUTH2_SCOPES)
  ManagedChannelBuilder.forAddress(host, port).build
}

val managedChannelStream: Stream[IO, ManagedChannel] =
  ManagedChannelBuilder
    .forAddress("127.0.0.1", 9999)
    .usePlaintext(true)
    .stream[IO]


val k = KindExpression("metadata")
val pr = PropertyReference("autoId")
val po = PropertyOrder(Some(pr), Direction.DESCENDING)
val q = Query(kind = Seq(k), order = Seq(po), limit = Some(1))
