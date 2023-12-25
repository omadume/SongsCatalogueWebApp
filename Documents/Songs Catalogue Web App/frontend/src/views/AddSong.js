import {useNavigate, useSearchParams} from "react-router-dom";
import {Button, Container, Form, FormGroup, Input, Label} from "reactstrap";
import React, {useEffect, useState} from "react";

export const AddSong = () => {
    const [catalogue, setCatalogue] = useState({});
    const [searchParams, setSearchParams] = useSearchParams();
    const id = searchParams.get("user_id");
    let navigate = useNavigate();

    useEffect(() => {
        if (id){
            fetch(`/users/catalogue?user_id=${id}`)
                .then(response => response.json())
                .then(data =>
                    fetch(`/catalogues/${data}`)
                        .then(response => response.json())
                        .then(data => setCatalogue(data))
                );
        }

    }, []);

    const submitForm = (event) => {
        event.preventDefault(); // Prevent form from reloading automatically after submission
        const formData = new FormData(event.target);
        const formDataObj = Object.fromEntries(formData.entries());
        fetch("/songs", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                songName: formDataObj.songName,
                artistName: formDataObj.artistName,
                albumName: formDataObj.albumName,
                releaseYear: formDataObj.releaseYear,
                durationSecs: formDataObj.durationSecs,
                genre: formDataObj.genre,
                songDetails: formDataObj.songDetails,
                catalogue: catalogue
            })
        })
            .then(response => response.json())
            .then(data => {
                    if (data.status === 500){
                        window.alert(data.message); // Show error message which has been thrown
                    } else{
                        window.alert("Song successfully added.");
                        window.location.reload();
                    }
            });
    }

    const backToSongsCatalogue = () => {
        navigate(`/users/catalogue/songs?user_id=${id}`);
    }

    return (
        <div>
            <Container fluid>
                <br/>
                <h3>Add a song to your catalogue</h3>
                <Form className="form my-3" onSubmit={(event) => submitForm(event)}>
                    <FormGroup>
                        <Label for="songName">Song Name</Label>
                        <Input type="text" name="songName" id="songName" className="w-50 my-1" required={true}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="artistName">Artist Name</Label>
                        <Input type="text" name="artistName" id="artistName" className="w-50 my-1" required={true}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="albumName">Album Name</Label>
                        <Input type="text" name="albumName" id="albumName" className="w-50 my-1" required={true}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="releaseYear">Release Year</Label>
                        <Input type="number" name="releaseYear" id="releaseYear" className="w-50 my-1" min={0} required={true}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="durationSecs">Duration (secs)</Label>
                        <Input type="number" name="durationSecs" id="durationSecs" className="w-50 my-1" min={0} required={true}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="genre">Genre</Label>
                        <Input type="text" name="genre" id="genre" className="w-50 my-1" required={true}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="songDetails">Details</Label>
                        <Input type="textarea" name="songDetails" id="songDetails" className="w-50 my-1"/>
                    </FormGroup>
                    <br/>
                    <Button className="me-1" color="primary">Submit</Button>
                    <Button type="button" className="me-1" color="secondary" onClick={backToSongsCatalogue}>Back</Button>
                </Form>
            </Container>
        </div>
    )
}