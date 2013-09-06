import sbt._
import Keys._
import sbtandroid.AndroidPlugin._

object BuildSettings {
  val androidVerCd = 0
  lazy val basicSettings = Seq(
    version               := "1.0." + androidVerCd,
    organization          := "Scaloid",
    scalaVersion          := "2.10.2",
    resolvers             ++= Dependencies.resolutionRepos,
    scalacOptions         := Seq(
      "-encoding", "utf8",
      "-target:jvm-1.6"
    ),
    javacOptions          ++= Seq(
      "-encoding", "utf8",
      "-source", "1.6",
      "-target", "1.6")
  )

}

object Dependencies {
  val resolutionRepos = Seq(
    "typesafe releases" at "http://repo.typesafe.com/typesafe/releases",
    "typesafe snapshots" at "http://repo.typesafe.com/typesafe/snapshots"
  )

  def compile   (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")
  def provided  (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")
  def test      (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")
  def runtime   (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "runtime")
  def container (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "container")

  val scaloid = "org.scaloid" %% "scaloid" % "2.3-8"
}

object ExamplesBuild extends Build {
  import BuildSettings._
  import Dependencies._

  // configure prompt to show current project
  override lazy val settings = super.settings :+ {
    shellPrompt := { s => Project.extract(s).currentProject.id + "> " }
  }

  // root project
  lazy val examples = Project("examples", file("."))
    .aggregate(hello)

  // sub-projects
  lazy val hello = AndroidProject("hello", file("hello"))
    .settings(basicSettings: _*)
    .settings(libraryDependencies ++= Seq(scaloid))
    .settings(
      Seq(
        versionCode := androidVerCd,
        platformName := "android-16",
        useProguard := true,
        proguardOptions += androidProguard): _*)

  // proguard android options string
  val androidProguard = """
    -target 6
    -keep class scala.collection.SeqLike { public protected *; }
  """

}
