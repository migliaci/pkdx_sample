package com.ign.pokedex.sample
import com.mongodb.casbah.Imports._ //note that _ == *, and therefore is bad.

/**
 * Hello Unova!
 *
 */

/*
In order to build a Scala compatible Maven project, run the following from a directory
that has access to the mvn command:
  mvn org.apache.maven.plugins:maven-archetype-plugin:1.0-alpha-7:create -DarchetypeGroupId=org.scala-tools.archetypes -DarchetypeArtifactId=scala-archetype-simple -DarchetypeVersion=1.2 -DremoteRepositories=https://oss.sonatype.org/content/groups/scala-tools/ -DgroupId=com.ign.pokedex.sample -DartifactId=pkdxsample
 */
object App {

  def main (args: Array[String]) {
    println( "Hello Unova!" )
    println( "Here we go! ")
    connectToDB()
    println( "Bai" )

  }

  //clear database
  //create database
  //add stuff
  //delete stuff
  //start mapping out teh pokemans

  def getTestObject(): MongoDBObject = {

    /*
    val builder = MongoDBObject.newBuilder
    builder += "pokemonId" -> "001BulbasaurV"
    val newObj = builder.result
    */

    val objectToReturn = MongoDBObject(
    "pokemonId" -> "001BulbasaurV",
      "metadata" -> MongoDBObject(
        "name" -> "Bulbasaur",
        "imageURL"->"someUrl",
        "generation"->1,
        "height"->10,
        "weight"->20,
        "form"->"normal",
        "eggCycles"->21,
        "maleGenderPercent"->89,
        "species"->"someSpecies",
        "primaryType"->"grass",
        "secondaryType"->"",
        "attack"->44,
        "specialAttack"->45,
        "defense"->30,
        "specialDefence"->45,
        "speed"->45,
        "hp"->100,
        "nationalId"->"001")
    )
    return objectToReturn
  }

  def cleanupDB(m: MongoCollection) = {
    m.drop();
  }

  def connectToDB() {
    val mongoColl = MongoConnection()("pokedex")("test_data")

    //save to the DB
    mongoColl += this.getTestObject()
    mongoColl.find()

    //val q  = MongoDBObject.empty
    //val fields = MongoDBObject("name" -> "Bulbasaur", "nationalId" -> "001", "testMetadata" -> "meta")
    val q = MongoDBObject("pokemonId" -> "001BulbasaurV")
    for (x <- mongoColl.find(q)) println(x)

    /*
    val user1 = MongoDBObject("user" -> "mmigliacio",
      "email" -> "~~migliacio~~<AT>ignDOTcom")
    val user2 = MongoDBObject("user" -> "someOtherUser")
    mongoColl += user1
    mongoColl += user2
    mongoColl.find()


    val q = MongoDBObject("user" -> "someOtherUser")
    val cursor = mongoColl.find(q)
    val user = mongoColl.findOne(q)
    println(user)
    */

    cleanupDB(mongoColl)
  }

}
