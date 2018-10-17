import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import CourseList from './containers/CourseList';

class App extends Component {

    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={CourseList}/>
                </Switch>
            </Router>
        )
    }
}

export default App;