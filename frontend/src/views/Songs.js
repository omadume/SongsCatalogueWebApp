import {useNavigate, useSearchParams} from "react-router-dom";
import {Button, ButtonGroup, Container, Input, Table} from "reactstrap";
import React, {useEffect, useState} from "react";

export const Songs = () => {
    const [songs, setSongs] = useState([]);
    const [filterInput, setFilterInput] = useState("");
    const [searchParams, setSearchParams] = useSearchParams();
    const id = searchParams.get("user_id");
    let navigate = useNavigate();

    useEffect(() => {
        if (id){
            fetch(`/users/catalogue/songs?user_id=${id}`)
                .then(response => response.json())
                .then(data => {
                    setSongs(data);
                });
        }

    }, []);

    const checkFilterInput = (event) => {
        setFilterInput(event.target.value);
    }

    const filterByYear = () => {
        if (id && filterInput){
            fetch(`/users/catalogue/songs?user_id=${id}&release_year=${filterInput}`)
                .then(response => response.json())
                .then(data => setSongs(data));
            setSearchParams({user_id: id, release_year: filterInput});
        }
    }

    const filterByName = () => {
        if (id && filterInput){
            fetch(`/users/catalogue/songs?user_id=${id}&artist_name=${filterInput}`)
                .then(response => response.json())
                .then(data => setSongs(data));
            setSearchParams({user_id: id, artist_name: filterInput});
        }
    }

    const resetPage = () => {
        window.location.reload();
        window.location.href = `/users/catalogue/songs?user_id=${id}`;
    }

    const addToSongsCatalogue = () => {
        navigate(`/users/catalogue/songs/add?user_id=${id}`);
    }

    const backToLogin = () => {
        navigate("/login");
    }

    const showSongDetails = (songName) => {
        const detailsPage = window.open("", "_blank");
        detailsPage.document.write(songName);
    }

    const songList = Array.from(songs).map(song => {
        return <tr key={song.id}>
            <td>{song.songName}</td>
            <td>{song.artistName}</td>
            <td>{song.albumName}</td>
            <td>{song.releaseYear}</td>
            <td>{song.durationSecs}</td>
            <td>{song.genre}</td>
            <td>
                <ButtonGroup>
                    <Button size="sm" color="info" onClick={() => showSongDetails(song.songDetails)}>Details</Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <div>
        <Container fluid>
                <br/>
                <h3>Your Songs</h3>
                <Button className="me-2 my-3" color="primary" onClick={addToSongsCatalogue}>Add Song</Button>
                <Button className="me-2 my-3" color="secondary" onClick={backToLogin}>Logout</Button>
                <h6>Filter</h6>
                <Input className="me-2 w-50" onChange={checkFilterInput}/>
                <ButtonGroup>
                    <Button className="me-1" size="sm" color="secondary" onClick={filterByYear}>By Release Year</Button>
                    <Button className="me-1" size="sm" color="secondary" onClick={filterByName}>By Artist Name</Button>
                    <Button className="me-1" size="sm" color="secondary" onClick={resetPage}>Reset</Button>
                </ButtonGroup>

                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="16%">Song Title</th>
                        <th width="16%">Artist Name</th>
                        <th width="16%">Album Name</th>
                        <th width="16%">Release Year</th>
                        <th width="16%">Duration (secs)</th>
                        <th width="16%">Genre</th>
                        <th width="20%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {songList}
                    </tbody>
                </Table>
            </Container>
        </div>
    )
}

