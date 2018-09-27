import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import CourseList from './containers/CourseList';
import { CookiesProvider } from 'react-cookie';

class App extends Component {

    render() {
        return (
            <CookiesProvider>
                <Router>
                    <Switch>
                        <Route path='/' exact={true} component={CourseList}/>
                    </Switch>
                </Router>
            </CookiesProvider>
        )
    }
}

export default App;