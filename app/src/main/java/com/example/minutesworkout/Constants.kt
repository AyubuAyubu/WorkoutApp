package com.example.minutesworkout

object Constants {
	fun defaultExerciseList():ArrayList<ExerciseModel>{

		//exerciseList is empty
		val exercisList=ArrayList<ExerciseModel>()

		val jumpingJacks=ExerciseModel(
			1,
			"Jumping Jacks",
			R.drawable.ic_jumping_jacks,
			false,
			false
		)
		//add our exerciseList
		exercisList.add(jumpingJacks)

		val wallSit=ExerciseModel(
			2,
			"Wall Sit",
			R.drawable.ic_wall_sit,
			false,
			false
		)
		exercisList.add(wallSit)

		val pushUp=ExerciseModel(
			3,
			"Push Up",
			R.drawable.ic_push_up,
			false,
			false
		)
		exercisList.add(pushUp)

		val abdnominalCrunch=ExerciseModel(
			4,
			"Abdnominal Crush",
			R.drawable.ic_abdominal_crunch,
			false,
			false
		)
		exercisList.add(abdnominalCrunch)


		val lunge=ExerciseModel(
			5,
			"Lunge",
			R.drawable.ic_lunge,
			false,
			false
		)
		exercisList.add(lunge)

		return exercisList
	}
}