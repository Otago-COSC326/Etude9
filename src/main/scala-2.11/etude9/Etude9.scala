package etude9

import java.util.{UUID, Scanner}

import _root_.etude5.Strip

import scala.collection.mutable.ListBuffer

/**
 * Created by tinhtooaung on 12/05/15.
 */
object Etude9 {

  def main(args: Array[String]): Unit = {

    if(args.isEmpty || args(0) == "-b" && args.length != 3){
      println("Usage:")
      println("\tjava -jar etude9.jar [base] [number]")
      println("\tjava -jar etude9.jar -b [base1] [base2]")
      return
    }

    println("all good")

  }
}
