import React from 'react';
import GroupOptionComponent from './GroupOptionComponent'

const SingleCourseComponent = (props) => {

    console.log(props)

    let chosenCourse = props.selectedOption.map(course => {
        let name = course.courseName;
        return (
            <li style={{backgroundColor: "red"}}>
                {name}
                <br/>
                <GroupOptionComponent
                    groupOptions = {course.courseGroups}
                />
            </li>
        )
    })

    // const chosenCourse = <li>some data</li>;

    return (
        <div>
            <ul>
                {chosenCourse}
            </ul>
        </div>


    )
}

export default SingleCourseComponent;