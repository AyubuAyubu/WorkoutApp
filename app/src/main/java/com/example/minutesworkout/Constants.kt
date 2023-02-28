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

		val plank=ExerciseModel(
			6,
			"Plank",
			R.drawable.ic_plank,
			false,
			false
		)
		exercisList.add(plank)

		val pushUpRotation=ExerciseModel(
			7,
			"Rotational Push up",
			R.drawable.ic_push_up_rotation,
			false,
			false
		)
		exercisList.add(pushUpRotation)

		val sidePlank=ExerciseModel(
			8,
			"Side Plank",
			R.drawable.ic_side_plank,
			false,
			false
		)
		exercisList.add(sidePlank)

		val squat=ExerciseModel(
			9,
			"Squat",
			R.drawable.ic_squat,
			false,
			false
		)
		exercisList.add(squat)

		val triceps=ExerciseModel(
			10,
			"Triceps on Chair",
			R.drawable.ic_triceps_dip_on_chair,
			false,
			false
		)
		exercisList.add(triceps)

		val highknees=ExerciseModel(
			11,
			"High Knees Running",
			R.drawable.ic_high_knees_running,
			false,
			false
		)
		exercisList.add(highknees)

		return exercisList
	}
}