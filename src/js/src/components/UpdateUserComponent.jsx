import React, { useState, useEffect } from 'react'
import UserService from '../services/UserService';
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";

export const UpdateUserComponent = () => {

	let navigate = useNavigate();
	let { id } = useParams();

	const [name, setName] = useState("");
	const [email, setEmail] = useState("");
	const [buddy, setBuddy] = useState("");

	useEffect(async () => {

		if (id) {			
		console.log('id => ' + JSON.stringify(id));
			const res = await UserService.getUserById(id);
			let newUser = res.data;
			setBuddy((newUser.buddy && newUser.buddy.name) ? newUser.buddy.name : '');
			setName(newUser.name);
			setEmail(newUser.email);
		}
	}, id);

	const saveOrUpdateUser = (e) => {
		e.preventDefault();
		let user = { name: name, email: email };
		if (!id) {
			UserService.createUser(user).then(res => {
 				id=res.data.id
		        navigate(`/view-user/${id}`);
			});
		} else {
 			UserService.updateUser(user, id);
		    navigate(`/view-user/${id}`);
		}
	}


	const changeNameHandler = (event) => {
		setName(event.target.value)
	}

	const changeEmailHandler = (event) => {
		setEmail(event.target.value)
	}

	const cancel = () => {
		navigate('/');
	}

	const getTitle = () => {
		if (!id) {
			return <h3 className="text-center">Add User</h3>
		} else {
			return <h3 className="text-center">Update User</h3>
		}
	}

	return (
		<div>
			<br></br>
			<div className="container">
				<div className="row">
					<div className="card col-md-6 offset-md-3 offset-md-3">
						{
							getTitle()
						}
						<div className="card-body">
							<form>
								<div className="form-group">
									<label> Name: </label>
									<input placeholder="Name" name="name" className="form-control"
										value={name} onChange={changeNameHandler} />
								</div>
								<div className="form-group">
									<label> Email: </label>
									<input placeholder="Email Address" name="email" className="form-control"
										value={email} onChange={changeEmailHandler} />
								</div>
								<div className="form-group">
									<label> Prayer Buddy: </label>
									<input name="email" className="form-control"
										value={buddy} disabled />
								</div>
								<button className="btn btn-success" onClick={saveOrUpdateUser}>Save</button>
								<button className="btn btn-danger" onClick={cancel} style={{ marginLeft: "10px" }}>Cancel</button>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
	)

}

export default UpdateUserComponent
