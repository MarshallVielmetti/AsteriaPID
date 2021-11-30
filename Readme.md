# Asteria PID Example

Hello,
Sorry I'm not there yet. I quickly put this together so that you guys can get started. I should be there around 6:30.

Don't clone this repository, or try copy pasting the code. It probably will break something, and the point is for you guys to learn how to do this yourself.
Instead, use this document as a reference, and if you get really stuck look at the code I wrote. But be warned, I probably had half an hour to do that so take every line with a grain of salt.

I included two different examples. We're going to try building the WPILib one first, and if that works, then we'll look at using the integrated MotionMagic in the Talons.
The code I have here probably doesn't work (because I haven't had a chance to test it yet) and the point of this exercise isn't to copy down code -- use what I have here as help, not the first source.

Different from yesterday, we will be working with Asteria, not the testbench. Connect to the Rio with an A to B cable.

## FIRST Steps

We need to get started with Command Based robot, so create one through the WPILib button (Create New Project)
You should see a RobotContainer, and Subsystem / Command folders.
First, create a new Subsystem for the arm, based on the ExampleSubsystem. Call it ArmSubsystem.
Instead of 'extending' SubsystemBase, extend the PIDSubsystem class. More on that later.

In the arm subsystem, define the motor (id 24 on Asteria), and see if you can at make the error saying 'unimplemented methods' go away.
Note that this is a TalonSRX as opposed to a SparkMAX like we've seen in the past.

First, try and find the motor encoder on the Arm motor - it has a funny wire attached to it.
The encoder lets us get the position of the arm at differnet points, and its how we will implement PID control.

Now we should be ready to get started.

---

## WPILib PIDSubsystem

The first method we'll look at is using the PIDSubsystem class to use PID.
Check the [PIDSubsystem Documetation](https://docs.wpilib.org/en/stable/docs/software/commandbased/pid-subsystems-commands.html)

See if you can use the examples to try and start actually implementing the methods from before.
What should the 'use output' method do?
What should the 'get measurement' method do?

## TalonSRX Motion Magic

To be honest I have to submit a college application so I don't have time to write this and I will proably be there before you get this far so don't worry about it.
