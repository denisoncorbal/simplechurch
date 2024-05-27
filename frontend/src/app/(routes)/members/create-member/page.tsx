'use client'
import { Gender, Member, MemberCommunicantRequestDto, MemberNonCommunicantRequestDto, VOID_MEMBER } from "@/app/interfaces/interfaces";
import Box from "@mui/material/Box/Box";
import Button from "@mui/material/Button/Button";
import MenuItem from "@mui/material/MenuItem/MenuItem";
import Select, { SelectChangeEvent } from "@mui/material/Select/Select";
import Step from "@mui/material/Step/Step";
import StepLabel from "@mui/material/StepLabel/StepLabel";
import Stepper from "@mui/material/Stepper/Stepper";
import TextField from "@mui/material/TextField/TextField";
import Typography from "@mui/material/Typography/Typography";
import { DateField } from "@mui/x-date-pickers";
import dayjs from "dayjs";
import { FormEvent, useContext, useState } from "react";

// TODO
export default function CreateMember() {
    const [activeStep, setActiveStep] = useState(0);
    const steps = ['Personal data', 'Reception data', 'Specific data'];
    const [member, setMember] = useState<Member>(VOID_MEMBER);
    const [memberCommunicant, setMemberCommunicant] = useState<MemberCommunicantRequestDto>();
    const [memberNonCommunicant, setMemberNonCommunicant] = useState<MemberNonCommunicantRequestDto>();

    const handleNext = () => {
        setActiveStep((prevActiveStep) => prevActiveStep + 1);
    };

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };

    const handleReset = () => {
        setActiveStep(0);
    };

    const handleChangeGenderSelect = (e: SelectChangeEvent) => {
        setMember({ ...member, gender: (e.target.value === "MALE" ? Gender.MALE : Gender.FEMALE) });
    }

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        console.log(e);
    }

    function Step1() {
        return (
            <>
                {/* TODO
                {auth!.user.churchId ? <TextField
                    required
                    id="churchId"
                    label="Church Id"
                    value={auth!.user.churchId}
                    disabled={true}
                /> : <TextField
                    required
                    id="churchId"
                    label="Church Id"
                    placeholder='Example'
                />} */}

                <TextField
                    required
                    id="fullName"
                    label="Full Name"
                    placeholder='Example'
                    focused={true}
                />

                <DateField
                    required
                    id="birthDate"
                    label="Birth Date"
                    defaultValue={dayjs(Date.now())}
                />

                <TextField
                    required
                    id="placeBirth"
                    label="Place Birth"
                    placeholder='Example'
                />
                <Select
                    id="gender"
                    label="Gender"
                    value={member.gender.toString()}
                    onChange={handleChangeGenderSelect}
                >
                    {Object.keys(Gender).map((gender) => {
                        return (<MenuItem key={gender} value={gender}>{gender}</MenuItem>)
                    })}
                </Select>
            </>
        )
    }
    function Step2() {
        return (
            <>
                <p>Celebrant Full Name</p>
                <p>Reception Date</p>
                <p>Reception Place</p>
                <p>Reception Mode</p>
                <p>Is Communicant?</p>
            </>
        )
    }
    function Step3() {
        return (
            <>
                <p>Only communicant options</p>
            </>
        )
    }
    function Step4() {
        return (
            <>
                <p>Only non communicant options</p>
            </>
        )
    }

    return (
        <Box sx={{ width: '100%' }}>
            <Stepper activeStep={activeStep}>
                {steps.map((label, index) => {
                    const stepProps: { completed?: boolean } = {};
                    const labelProps: {
                        optional?: React.ReactNode;
                    } = {};
                    return (
                        <Step key={label} {...stepProps}>
                            <StepLabel {...labelProps}>{label}</StepLabel>
                        </Step>
                    );
                })}
            </Stepper>
            <form onSubmit={(e) => { handleSubmit(e) }}>
                {activeStep === steps.length ? (
                    <>
                        <Typography sx={{ mt: 2, mb: 1 }}>
                            All steps completed - you&apos;re finished
                        </Typography>
                        <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                            <Box sx={{ flex: '1 1 auto' }} />
                            <Button onClick={handleReset}>Reset</Button>
                        </Box>
                    </>
                ) : (
                    <>
                        {activeStep === 0 ? <Step1 /> : <></>}
                        {activeStep === 1 ? <Step2 /> : <></>}
                        {activeStep === 2 ? <Step3 /> : <></>}
                        {activeStep === 3 ? <Step4 /> : <></>}
                        <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                            <Button
                                color="inherit"
                                disabled={activeStep === 0}
                                onClick={handleBack}
                                sx={{ mr: 1 }}
                            >
                                Back
                            </Button>
                            <Box sx={{ flex: '1 1 auto' }} />
                            {activeStep === steps.length - 1 ? <Button type="submit">Finish</Button> : <Button onClick={handleNext}>Next</Button>}
                        </Box>
                    </>
                )}
            </form>
        </Box>
    );
}