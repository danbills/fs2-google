
```scala
 val OAUTH2_SCOPES = List("https://www.googleapis.com/auth/cloud-platform").asJava
  def channel(host: String, port: Int): ManagedChannel = {
    val creds = GoogleCredentials.getApplicationDefault().createScoped(OAUTH2_SCOPES)
    //val creds = GoogleCredentials.getApplicationDefault()
    ManagedChannelBuilder.forAddress(host, port)
```
