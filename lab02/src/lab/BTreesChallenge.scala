package lab

object BTreesChallenge extends App {

  // A custom and generic binary tree of elements of type A
  sealed trait Tree[A]
  object Tree {
    case class Leaf[A](value: A) extends Tree[A]
    case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

    def size[A](t: Tree[A]): Int = treeVisitor[A, Int](t, _ + _, _ => 1)

    def find[A](t: Tree[A], elem: A): Boolean = treeVisitor[A, Boolean](t,  _ || _, _ == elem)

    def count[A](t: Tree[A], elem: A): Int = treeVisitor[A, Int](t, _ + _, {
      case e if e==elem => 1
      case _ => 0
    })

    def treeVisitor[A, B](t:Tree[A],
                          operator: (B, B) => B,
                          leafFunction: A => B): B = t match {
      case Branch(left, right) => operator(
        treeVisitor(left, operator, leafFunction),
        treeVisitor(right, operator, leafFunction))
      case Leaf(value) => leafFunction(value)
    }

  }

  import Tree._
  val tree = Branch(Branch(Leaf(1),Leaf(2)),Leaf(1))
  println(tree, size(tree)) // ..,3
  println( find(tree, 1)) // true
  println( find(tree, 4)) // false
  println( count(tree, 1)) // 2
}
