import React from 'react';
import Select from 'react-select';
import {Container, Form, FormGroup, Label, Button} from 'reactstrap';

const searchComponent = (props) => {

    const courseOptions = props.filteredCourses.map(course => {
        return {value: course.courseNum, label: course.courseName};
    });

    // let groupOptions = props.selectedOption.map(group => {
    //     return {
    //         value: group,
    //         label: (
    //             `Course Number: ${group.courseGroupId.courseNum}, \n
    //             Location: ${group.courseGroupId.groupLocation}, \n
    //             Group Number: ${group.courseGroupId.groupNum}, \n
    //             Day: ${group.day}, \n
    //             Hours: ${group.hours}, \n
    //             Semester: ${group.courseGroupId.semesterName}, \n
    //             Teaching Type: ${group.teachingType}\n`
    //         )
    //     }
    // });

    let groupOptions = props.selectedOption.map(group => {
        return {
            value: group,
            label: (
                `Course Number: ${group.courseGroupId.courseNum}, \n
                Location: ${group.courseGroupId.groupLocation}, \n 
                Group Number: ${group.courseGroupId.groupNum}, \n
                Day: ${group.day}, \n
                Hours: ${group.hours}, \n
                Semester: ${group.courseGroupId.semesterName}, \n
                Teaching Type: ${group.teachingType}\n`
            )
        }
    });

    let placeholder, disabled;
    if (props.selectedOption.length === 0) {
        placeholder = "No groups available";
        disabled = true;
    } else {
        placeholder = "Choose Group";
        disabled = false;
    }

    let groupPlaceholder;

    function handleChosenGroup(key, data) {
        console.log('chosen group:\n' + data.courseName);
        groupPlaceholder =  key;
        let temp = props.selectedOption.filter(option => {
                    return (JSON.stringify(option.courseGroupId).toString() === JSON.stringify(key.courseGroupId).toString())
                })
        groupPlaceholder = temp;
    }

    return (
        <div className="search-course">
            <Container>
                <Form>
                    <FormGroup>
                        <Label for="courseNum">Choose Course Number or Name</Label>
                        <Select options={courseOptions}
                                valueKey='courseNum'
                                lableKey='courseName'
                                placeholder={'Search for a course'}
                                autoComplete='courseName'
                                onChange={(courseObject) => props.handleChosenCourse(courseObject)}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label for="courseNum">Choose a Course Group</Label>
                        <Select
                            options={groupOptions}
                            placeholder={placeholder}
                            isDisabled={disabled}
                            onChange={(chosenGroup) => handleChosenGroup(chosenGroup.value, props.chosenCourseData)}
                        />
                    </FormGroup>
                    <Button color="danger" active onClick={(event) => {
                        props.handleAddingCourseToCalendar(event, groupPlaceholder, props.chosenCourseData);
                    }}>Add Course</Button>
                </Form>
            </Container>
        </div>
    )
};

export default searchComponent;