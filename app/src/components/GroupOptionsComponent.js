import React from 'react';
import Select from 'react-select';

const groupOptionsComponent = (props) => {

    let groupPlaceholder;
    function func (key) {
        groupPlaceholder =  key;
        let temp = props.selectedOption.filter(option => {
            return (JSON.stringify(option.courseGroupId).toString() === JSON.stringify(key).toString())
        })
        groupPlaceholder = temp;
    }

    return (

    )
};

export default groupOptionsComponent;