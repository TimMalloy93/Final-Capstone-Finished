<template>
    <form v-on:submit.prevent="addExerciseToWorkout">
        <label for="sets">Sets</label>
        <input type="number" id="sets" v-model="editWorkout.sets">
        <label for="reps">Reps</label>
        <input type="number" id="reps" v-model="editWorkout.reps">
        <label for="weight">Weight (lbs)</label>
        
        <select type="number" id="weight" v-model="editWorkout.weight">
            <option v-for="weight in weightRange" :key="weight">{{ weight }}</option>
        </select>
        <button type="submit" class="button-link">Add Exercise</button>
        <button type="reset" v-on:click="cancelExercise" class="button-link">Back to Exercise Selection</button>
    </form>
</template>
<script>
import WorkoutService from '../services/WorkoutService';

export default {
    props: {
        workout: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            editWorkout: {
                exerciseId: this.$route.params.id,
                sets: null,
                reps: null,
                weight: null,
                duration: null
            }
        };
    },
    methods: {
        cancelExercise() {
            this.$router.go(-1);
        },
        addExerciseToWorkout(editWorkout) {
            WorkoutService.createWorkout(this.editWorkout)
                .then(() => {
                    console.log("Exercise added to workout: " + this.editWorkout);
                    this.$router.go(-1);
                })
                .catch((error) => {
                    console.log(error);
                });

        }
    },
    computed: {
        weightRange() {
            let weightRange = [];
            for (let i = 5; i <= 200; i+= 5) {
                weightRange.push(i);
            }
            return weightRange;
        }
    } 
}
</script>

<style scoped>
form {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-gap: 10px;
    margin: 10px;
    padding: 10px;
    font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;

}

.button-link {
    display: inline-block; 
    padding: 5px; 
    background-color: #007bff; 
    color: white; 
    text-align: center; 
    text-decoration: none;
    border-radius: 5px; 
    border: 1px solid #007bff; 
    transition: background-color 0.3s, border-color 0.3s;
    font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;

}
.button-link:hover {
    background-color: #0056b3;
    border-color: #0056b3;
}

.button-link:active {
    background-color: #00408d;
    border-color: #00408d;
}
</style>