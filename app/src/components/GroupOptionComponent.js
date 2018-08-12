import React from 'react';

const groupOptionComponent = (props) => {

    let groups = props.groupOptions.map(courseDetail => {
            return (
                <li>
                    {courseDetail.courseGroupId.groupNum}
                    <br/>
                    {courseDetail.courseGroupId.groupLocation}
                    <br/>
                    {courseDetail.hours}
                    <br/>
                    {courseDetail.teachingType}
                </li>
            )
        }
    )

    return (
        <li style={{
            backgroundColor: "pink",
        }}>
            {groups}
        </li>
    )
}

export default groupOptionComponent