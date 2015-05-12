package etude9

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * Created by tinhtooaung on 12/05/15.
 */
object Etude9 {

  def main(args: Array[String]): Unit = {

    if(args.isEmpty ||
      (args(0) == "-b" && args.length != 3) ||
      (args(0) != "-b" && args.length != 2)){
      println("Usage:")
      println("\tjava -jar etude9.jar [base] [number]")
      println("\tjava -jar etude9.jar -b [base1] [base2]")
      return
    }

    if(args.length == 3){
      executePart2(args)
    }else{
      executePart1(args)
    }
  }

  def executePart1(args: Array[String]): Unit ={
    val candidates = findBlocks(args(0).toInt, args(1).toInt)
    val result = processBlock(candidates.toMap)
    result match {
      case Some((number, block)) => println(s"$number $block")
      case None =>
    }
  }

  def executePart2(args: Array[String]): Unit ={
    val base1 = args(1).toInt
    val base2 = args(2).toInt

    var result: Option[Int] = None
    if(base1 == 1 || base2 == 1){
      return
    }

    var count = List(base1, base2).max
    while(result.isEmpty){
      val candidate1 = convertToBase(base1, count)
      val candidate2 = convertToBase(base2, count)
      if(isRepeated(candidate1) && isRepeated(candidate2)){
        result = Some(count)
      }else{
        count += 1
      }
    }

    result match {
      case Some(n) => println(n)
      case None =>
    }
  }


  def findBlocks(base: Int, number: Int): Map[Int, Int] = {
    val candidates = mutable.HashMap.empty[Int, Int].withDefaultValue(0)
    var result = List.empty[Int]
    var current_number = 1
    for(i <- 1 until number){
      result = convertToBase(base, i)
      if(result.distinct.length != result.length){
        candidates(current_number) = candidates(current_number) + 1
      }else{
        current_number = i + 1
      }
    }
    candidates.toMap
  }

  def convertToBase(base: Int, number: Int): List[Int] = {
    val remainder = ArrayBuffer.empty[Int]
    var numberCopy = number
    do{
      remainder += (numberCopy % base)
      numberCopy = numberCopy/base
    }while(numberCopy != 0)
    remainder.reverse.toList
  }

  def isRepeated(list: List[Int]): Boolean = {
    list.distinct.length != list.length
  }

  def processBlock(candidates: Map[Int, Int]): Option[(Int, Int)] = {
    var candidate: Option[(Int, Int)] = None
    candidates.foreach { can =>
      if (candidate.isEmpty){
        candidate = Some(can)
      }else if(can._2 > candidate.get._2){
        candidate = Some(can)
      }else if(can._2 == candidate.get._2){
        if(can._1 < candidate.get._1){
          candidate = Some(can)
        }
      }
    }
    candidate
  }

  def processBlock(candidates1: Map[Int, Int], candidates2: Map[Int, Int]): Option[Int] = {
    var numbers1 = List.empty[Int]
    candidates1.foreach { can =>
      for(i <- can._1 to can._2){
        numbers1 = i :: numbers1
      }
    }

    var numbers2 = List.empty[Int]
    candidates2.foreach { can =>
      for(i <- can._1 to can._2){
        numbers2 = i :: numbers2
      }
    }

    println(numbers1)
    println(numbers2)
    val intersect = numbers1.intersect(numbers2)
    if(intersect.nonEmpty){
      Some(intersect.min)
    }else{
      None
    }
  }
}
