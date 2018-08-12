import React from 'react';
import {Button, ButtonGroup, Table} from 'reactstrap';
import {Link} from 'react-router-dom';

const courseTableComponent = (props) => {

    const courseList = props.filteredCourses.map(course => {
        return (
            <tr key={course.courseNum}>
                <td style={{whiteSpace: 'nowrap'}}>{course.courseNum}</td>
                <td style={{whiteSpace: 'normal'}}>{course.courseName}</td>
                <td style={{whiteSpace: 'nowrap'}}>{course.courseCredit}</td>
                <td style={{whiteSpace: 'normal'}}>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link}
                                to={"/courses/" + course.courseNum}>Edit</Button>
                        <Button size="sm" color="danger"
                                onClick={() => props.remove(course.courseNum)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        )
    });

    return (
        <div className="courses">
            <Table className="mt-4">
                <thead>
                <tr>
                    <th width="20%">Course Number</th>
                    <th width="55%">Course Name</th>
                    <th width="20%">Course Credit</th>
                    <th width="5%">Actions</th>
                </tr>
                </thead>
                <tbody>
                    {courseList}
                </tbody>
            </Table>
        </div>
    )
};

export default courseTableComponent;